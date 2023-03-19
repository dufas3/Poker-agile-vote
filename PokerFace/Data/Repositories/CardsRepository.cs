using Microsoft.EntityFrameworkCore;
using PokerFace.Data.Common;
using PokerFace.Data.Entities;

namespace PokerFace.Data.Repositories
{
    public class CardsRepository : ICardsRepository
    {
        private readonly ApplicationDbContext context;

        public CardsRepository(ApplicationDbContext context)
        {
            this.context = context;
        }

        public async Task<Card> GetCardAsync(int id)
        {
            return await Task.Run(() => StaticSessionData.AllCards.FirstOrDefault(x => x.Id == id));
        }

        public async Task<List<Card>> GetCardsAsync()
        {
            return await context.Cards.ToListAsync();
        }

        public async Task SetCardsAsync()
        {
            StaticSessionData.AllCards = await GetCardsAsync();
        }
    }
}
