using PokerFace.Data.Entities;

namespace PokerFace.Data.Common
{
    public interface IUserRepository
    {
        Task<User> GetAsync(int id);
        Task<User> GetAsync(string socketId);
        Task DeleteAsync(User user);
        Task<User> GetModerator(string email, string password);
        Task UpdateAsync(User user);
        Task AddUserToSessionAsync(User user, string roomId);
        Task SetSelectedCardAsync(int userId, int cardId);
        Task<Card> GetSelectedCardAsync(int userId);
        Task<string> GetRoomId(int userId);
        Task SetSocketId(string socketId, int userId);
    }
}
