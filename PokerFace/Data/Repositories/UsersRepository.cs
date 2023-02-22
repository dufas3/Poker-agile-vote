using Microsoft.EntityFrameworkCore;
using PokerFace.Data.Entities;

namespace PokerFace.Data.Repositories
{
    public class UsersRepository
    {
        ApplicationDbContext context { get; set; }
        public static List<User> StaticUsers { get; set; }
        public UsersRepository(ApplicationDbContext context)
        {
            this.context = context;
            StaticUsers = new List<User>
           {
                new Data.Entities.User
                {
                    Id = 1,
                    Name = "Matas",
                    Password = "Passwd"

                },
                 new Data.Entities.User
                {
                    Id = 2,
                    Name = "Lukas",
                    Password = "Passw2d"

                }
           };
        }
        public async Task<List<User>> GetAllAsync()
        {
            return await Task.FromResult(context.Users.ToList());
        }
    }
}
