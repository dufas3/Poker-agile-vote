using PokerFace.Data.Entities;

namespace PokerFace.Data.Common
{
    public interface IUserRepository
    {
        Task<User> GetAsync(int id);
    }
}
