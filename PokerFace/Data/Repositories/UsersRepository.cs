using Microsoft.EntityFrameworkCore;
using PokerFace.Data.Common;
using PokerFace.Data.Entities;

namespace PokerFace.Data.Repositories
{
    public class UserRepository : IUserRepository
    {
        ApplicationDbContext context { get; set; }

        public UserRepository(ApplicationDbContext context)
        {
            this.context = context;
        }

        public async Task<List<User>> GetAllAsync()
        {
            return await context.Users.ToListAsync();
        }

        public async Task<User> GetAsync(int id)
        {
            return await context.Users.FirstOrDefaultAsync(x => x.Id == id);
        }

        public async Task UpdateAsync(User user)
        {
            context.Update(user);
            await context.SaveChangesAsync();
        }

        public async Task AddUserToSessionAsync(User user, string roomId)
        {
            var session = await Task.FromResult(context.Sessions.Where(x => x.RoomId == roomId).First());

            if (session == null)
                throw new BadHttpRequestException("There's no session with this Id!");

            context.Users.Update(user);
            await context.SaveChangesAsync();

            session.UserIds.Add(user.Id);
            context.Sessions.Update(session);
            await context.SaveChangesAsync();
        }

        public async Task SetSelectedCardAsync(int userId, int cardId)
        {
            var user = await context.Users.Where(x => x.Id == userId).FirstOrDefaultAsync();

            if (user == null)
                throw new BadHttpRequestException("There's no user with this Id!");

            user.SelectedCardId = context.Cards.Where(x => x.Id == cardId).First().Id;
            await context.SaveChangesAsync();
        }

        public async Task<Card> GetSelectedCardAsync(int userId)
        {
            var user = await context.Users.Where(x => x.Id == userId).FirstOrDefaultAsync();
            return await context.Cards.Where(x => x.Id == user.SelectedCardId).FirstOrDefaultAsync();
        }

        public async Task<string> GetRoomId(int userId)
        {
            return context.Users.FirstOrDefault(x => x.Id == userId).RoomId;
        }

        public async Task SetSocketId(string socketId, int userId)
        {
            var user = await context.Users.FirstOrDefaultAsync(x=>x.Id==userId);
            bool isNew = user == null;

            if (isNew)
                user = new User();

            user.ConnectionId = socketId;

            if (isNew)
                await context.Users.AddAsync(user);
            else
                context.Users.Update(user);

            await context.SaveChangesAsync();
        }

        public async Task<User> GetAsync(string ConnectionId)
        {
            return await context.Users.FirstOrDefaultAsync(x => x.ConnectionId == ConnectionId);
        }

        public async Task<User> GetModerator(string email, string password) 
        {
            var user = await context.Users.Where(x => x.Name == email && x.Password == password).FirstOrDefaultAsync();
            if (user == null)
                throw new BadHttpRequestException("No moderator by those credentials");
            return user;
        }
    }
}
