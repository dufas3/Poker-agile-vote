using PokerFace.Data.Common;
using PokerFace.Data.Entities;

namespace PokerFace.Data.Repositories
{
    public class SessionRepository : ISessionRepository
    {
        private readonly ApplicationDbContext context;

        public SessionRepository(ApplicationDbContext context)
        {
            this.context = context;
        }

        public async Task<List<Session>> GetAllAsync()
        {
            return await Task.FromResult(context.Sessions.ToList());
        }

        public async Task<List<User>> GetSessionUsersAsync(int roomId)
        {
            var session = await GetByRoomIdAsync(roomId);
            if (session == null)
                throw new BadHttpRequestException("No session by that id");
            var users = new List<User>();

            users = await Task.FromResult(context.Users.Where(x => x.RoomId == roomId).ToList());

            return users;
        }

        public async Task<Session> GetAsync(int id)
        {
            return await Task.FromResult(context.Sessions.Where(x => x.Id == id).First());
        }

        public async Task<Session> GetByRoomIdAsync(int id)
        {
            return await Task.FromResult(context.Sessions.Where(x => x.RoomId == id).First());
        }

        public async Task AddAsync(Session session)
        {
            await context.Sessions.AddAsync(session);
            await context.SaveChangesAsync();
        }
    }
}
