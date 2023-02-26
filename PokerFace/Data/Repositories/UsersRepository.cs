using Microsoft.EntityFrameworkCore;
using PokerFace.Data.Common;
using PokerFace.Data.Entities;

namespace PokerFace.Data.Repositories
{
    public class UserRepository : IUserRepository
    {
        ApplicationDbContext context { get; set; }

        public UserRepository(ApplicationDbContext context)
        {
            this.context = context;
        }

        public async Task<List<User>> GetAllAsync()
        {
            return await Task.FromResult(context.Users.ToList());
        }

        public async Task<User> GetAsync(int id)
        {
            return await Task.FromResult(context.Users.FirstOrDefault(x => x.Id == id));
        }
    }
}
