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

        public async Task<List<Card>> GetActiveCardsAsync(int roomId)
        {
            var session = await context.Sessions.FirstOrDefaultAsync(x => x.RoomId == roomId);

            if (session == null)
                throw new BadHttpRequestException("There's no session with this Id!");

            var activeCards = new List<Card>();

            foreach (var id in session.CardIds)
            {
                activeCards.Add(context.Cards.FirstOrDefault(x => x.Id == id));
            }

            return activeCards;
        }

        public async Task SetActiveCardsAsync(int roomId, List<int> cardIds)
        {
            var session = await context.Sessions.FirstOrDefaultAsync(x => x.RoomId == roomId);

            if (session == null)
                throw new BadHttpRequestException("There's no session with this Id!");

            session.CardIds.Clear();
            session.CardIds.AddRange(cardIds);

            context.Sessions.Update(session);
            await context.SaveChangesAsync();
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
    }
}
