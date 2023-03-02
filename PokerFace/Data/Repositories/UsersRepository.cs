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
            return await Task.FromResult(context.Users.ToList());
        }

        public async Task<User> GetAsync(int id)
        {
            return await Task.FromResult(context.Users.FirstOrDefault(x => x.Id == id));
        }

        public async Task UpdateAsync(User user)
        {
            await Task.FromResult(context.Update(user));
            await context.SaveChangesAsync();
        }

        public async Task AddUserToSessionAsync(User user, int roomId)
        {
            var session = await Task.FromResult(context.Sessions.Where(x => x.RoomId == roomId).First());

            if (session == null)
                throw new BadHttpRequestException("There's no session with this Id!");

            var ussr = await context.Users.AddAsync(user);

            session.UserIds.Add(ussr.Entity.Id);
            context.Sessions.Update(session);
            await context.SaveChangesAsync();
        }

        public async Task SetSelectedCardAsync(int userId, int cardId)
        {
            var user = await context.Users.Where(x => x.Id == userId).FirstOrDefaultAsync();

            if (user == null)
                throw new BadHttpRequestException("There's no user with this Id!");

            user.SelectedCardId = context.Cards.Where(x=>x.Id==cardId).First().Id;
            await context.SaveChangesAsync();
        }

        public async Task LogoutUserAsync(int userId, int roomId)
        {
            var session = await Task.FromResult(context.Sessions.Where(x => x.RoomId == roomId).First());
            if (session == null)
                throw new BadHttpRequestException("There's no session with this Id!");

            var user = await GetAsync(userId);
            if (user == null)
                throw new BadHttpRequestException("There's no user with this Id!");

            session.UserIds.Remove(user.Id);

            context.Update(session);
            context.Remove(user);
            await context.SaveChangesAsync();
        }

        public async Task<Card> GetSelectedCardAsync(int userId)
        {
            var user = context.Users.Where(x => x.Id == userId).FirstOrDefault();
            return await Task.FromResult(context.Cards.Where(x => x.Id == user.SelectedCardId).FirstOrDefault());  
        }
    }
}
