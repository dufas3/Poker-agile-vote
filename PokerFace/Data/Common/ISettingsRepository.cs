using PokerFace.Data.Entities;

namespace PokerFace.Data.Common
{
    public interface ISettingsRepository
    {
        Task<List<Setting>> GetSettingsAsync(string roomId);
        Task<List<Setting>> GetSettingsAsync();
        Task SetSettingsAsync(List<int> ids, string roomId);
    }
}
