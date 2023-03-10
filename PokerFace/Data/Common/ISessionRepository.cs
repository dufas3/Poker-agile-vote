using PokerFace.Data.Entities;

namespace PokerFace.Data.Common
{
    public interface ISessionRepository
    {
        Task<Session> GetAsync(int id);
        Task<List<Session>> GetAllAsync();
        Task AddAsync(Session session);
        Task<List<User>> GetSessionUsersAsync(string roomId);
        Task<Session> GetByRoomIdAsync(string roomId);
        Task LogoutSessionAsync(string roomId);
        Task LogoutUserAsync(int userId, string roomId);      
        Task<List<Card>> GetUserSelectedCardsAsync(string roomId);
        Task<SessionState> GetSessionStateAsync(string roomId);
        Task SetSessionStateAsync(string roomId, SessionState state);
        Task ClearVotes(string roomId);
        Task SetActiveCardsAsync(string roomId, List<int> cardIds);
        Task<List<Card>> GetActiveCardsAsync(string roomId);
        Task<int> GetModeratorId(string roomId);
    }
}
