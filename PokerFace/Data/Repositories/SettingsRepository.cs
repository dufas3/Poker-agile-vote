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

        public async Task<List<Setting>> GetSettingsAsync(string roomId)
        {
            var session = await StaticSessionData.GetSessionAsync(roomId);
            return session.Settings;
        }

        public async Task<List<Setting>> GetSettingsAsync()
        {
           return await context.Settings.ToListAsync();
        }

        public async Task SetSettingsAsync(List<Setting> settings, string roomId)
        {
            var session = await StaticSessionData.GetSessionAsync(roomId);
            session.Settings = settings;
            await StaticSessionData.SaveChangesAsync(session, roomId);
        }
    }
}
