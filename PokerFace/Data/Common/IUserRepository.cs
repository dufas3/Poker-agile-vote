using PokerFace.Data.Entities;

namespace PokerFace.Data.Common
{
    public interface IUserRepository
    {
        Task<User> GetAsync(int id);
        Task<User> GetAsync(string socketId);
        Task UpdateAsync(User user);
        Task AddUserToSessionAsync(User user, int roomId);
        Task SetSelectedCardAsync(int userId, int cardId);
        Task<Card> GetSelectedCardAsync(int userId);
        Task<int> GetRoomId(int userId);
        Task SetSocketId(string socketId, int userId);
    }
}
