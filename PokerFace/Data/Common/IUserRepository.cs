using PokerFace.Data.Entities;

namespace PokerFace.Data.Common
{
    public interface IUserRepository
    {
        Task<User> GetAsync(int id);
        Task UpdateAsync(User user);
        Task AddUserToSessionAsync(User user, int roomId);
        Task SetSelectedCardAsync(int userId, int cardId);
        Task LogoutUserAsync(int userId,int roomId);
    }
}
