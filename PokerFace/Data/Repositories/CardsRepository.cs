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

        public async Task<List<Card>> GetCardsAsync()
        {
            return await context.Cards.ToListAsync();

        } 

        public async Task SetActiveUserCardAsync(int cardId, int userId)
        {
            var user = await context.Users.FirstOrDefaultAsync(x => x.Id == userId);
            if (user == null)
                throw new BadHttpRequestException("no user");

            var card = context.Cards.Where(x => x.Id == cardId).First();

            user.SelectedCardId = card.Id;
            await context.SaveChangesAsync();
        }

        public async Task<Card> GetAsync(int cardId)
        {
            return await context.Cards.Where(x => x.Id == cardId).FirstOrDefaultAsync();
        }
    }
}
