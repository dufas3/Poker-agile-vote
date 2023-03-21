using Microsoft.EntityFrameworkCore;
using PokerFace.Data.Common;
using PokerFace.Data.Entities;

namespace PokerFace.Data.Repositories
{
    public class SettingsRepository : ISettingsRepository
    {
        private readonly ApplicationDbContext context;
        public SettingsRepository(ApplicationDbContext context)
        {
            this.context = context;
        }

        public async Task<List<Setting>> GetSettingsAsync()
        {

            return await context.Settings.ToListAsync();
        }
        public async Task SetSettingsAsync(List<Setting> settings)
        {
            context.Settings.UpdateRange(settings);

            await context.SaveChangesAsync();
        }
    }
}
