using PokerFace.Data.Entities;

namespace PokerFace.Data.Common
{
    public interface ICardsRepository
    {
        Task<List<Card>> GetCardsAsync();
        Task<Card> GetAsync(int cardId);
    }
}
