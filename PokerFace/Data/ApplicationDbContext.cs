using Microsoft.EntityFrameworkCore;
using PokerFace.Data.Common;
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
    "Server = tcp:pokerappusers.database.windows.net, 1433; Initial Catalog = Balandziaiusers; Persist Security Info = False; User ID = KitmBalandziai; Password =Balandis@; MultipleActiveResultSets = False; Encrypt = True; TrustServerCertificate = False; Connection Timeout = 30; ");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Session>()
                .HasKey(s => s.Id);
            
            modelBuilder.Entity<Session>()
                .Property(s => s.UserIds)
                .HasConversion(
                    v => string.Join(',', v),   
                    v => v.Split(',', StringSplitOptions.RemoveEmptyEntries).Select(int.Parse).ToList());
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Session> Sessions { get; set; }
    }
}