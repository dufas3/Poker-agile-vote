using Microsoft.EntityFrameworkCore;
using PokerFace.Data.Entities;

namespace PokerFace.Data
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                optionsBuilder.UseSqlServer(
    "Server=(localdb)\\mssqllocaldb;Database=PokerFaceDb;Trusted_Connection=True;MultipleActiveResultSets=true");
            }
        }

        public DbSet<User> Users { get; set; }
    }
}