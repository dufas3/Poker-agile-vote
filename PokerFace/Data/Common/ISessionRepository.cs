using PokerFace.Data.SessionModels;


namespace PokerFace.Data.Common
{
    public interface ISessionRepository
    {
        Task AddAsync(Entities.Session session);
        Task AddAsync(Entities.Moderator moderator, List<Entities.Setting> settings);

        Task UpdateAsync(Session session);

        Task<List<User>> GetSessionUsersAsync(string roomId);
        Task<Session> GetByRoomIdAsync(string roomId);
        Task<List<Entities.Card>> GetActiveCardsAsync(string roomId);
        Task<List<Entities.Card>> GetSessionUsersSelectedCardAsync(string roomId);
        Task<SessionState> GetSessionStateAsync(string roomId);
        Task<Entities.Session> GetSessionFromDb(string roomId);

        Task SetSessionStateAsync(string roomId, SessionState state);
        Task SetActiveCardsAsync(string roomId, List<int> cardIds);

        Task RemoveSession(string roomId);
        Task UpdateTimeStampAsync(string roomId, DateTime time);
        Task SetLastTimerAsync(string roomId);
        Task<DateTime> GetLastTimerAsync(string roomId);
    }
}
