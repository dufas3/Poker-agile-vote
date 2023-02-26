using PokerFace.Data.Entities;

namespace PokerFace.Data.Common
{
    public interface ISessionRepository
    {
        Task<Session> GetAsync(int id);
        Task<List<Session>> GetAllAsync();
        Task AddAsync(Session session);
        Task<List<User>> GetSessionUsersAsync(int sessionId);
    }
}
