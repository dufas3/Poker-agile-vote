using PokerFace.Data.Entities;

namespace PokerFace.Data.Common
{
    public interface ISessionRepository
    {
        Task<Session> GetAsync(int id);
        Task<List<Session>> GetAllAsync();
        Task AddAsync(Session session);
        Task<List<User>> GetSessionUsersAsync(int roomId);
        Task<Session> GetByRoomIdAsync(int id);
        Task LogoutSessionAsync(int roomId);
        Task<List<Card>> GetUserSelectedCards(int roomId);
    }
}
