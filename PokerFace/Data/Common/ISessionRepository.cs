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
        Task LogoutUserAsync(int userId,int roomId);      
        Task<List<Card>> GetUserSelectedCardsAsync(int roomId);
        Task<SessionState> GetSessionStateAsync(int roomId);
        Task SetSessionStateAsync(int roomId, SessionState state);
        Task ClearVotes(int roomId);
        Task SetActiveCardsAsync(int roomId, List<int> cardIds);
        Task<List<Card>> GetActiveCardsAsync(int roomId);
        Task<int> GetModeratorId(int roomId);
    }
}
