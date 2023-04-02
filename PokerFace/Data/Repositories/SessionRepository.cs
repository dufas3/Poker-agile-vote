using Microsoft.EntityFrameworkCore;
using PokerFace.Data.Common;
using PokerFace.Data.SessionModels;

namespace PokerFace.Data.Repositories
{
    public class SessionRepository : ISessionRepository
    {
        private readonly ApplicationDbContext context;

        public SessionRepository(ApplicationDbContext context)
        {
            this.context = context;
        }

        public async Task<List<User>> GetSessionUsersAsync(string roomId)
        {
            return await StaticSessionData.GetSessionUsersAsync(roomId);
        }

        public async Task<Session> GetByRoomIdAsync(string roomId)
        {
            return await StaticSessionData.GetSessionAsync(roomId);
        }

        public async Task AddAsync(Entities.Session session)
        {
            await context.Sessions.AddAsync(session);
            await context.SaveChangesAsync();
        }

        public async Task AddAsync(Entities.Moderator moderator, List<Entities.Setting> settings)
        {
            var session = await GetSessionFromDb(moderator.RoomId);
            
            StaticSessionData.AddSession(moderator, session.Id, settings);
        }

        public async Task<List<Entities.Card>> GetSessionUsersSelectedCardAsync(string roomId)
        {
            var users = await StaticSessionData.GetSessionUsersAsync(roomId);

            var cards = new List<Entities.Card>();

            foreach (var user in users)
            {
                if (user.SelectedCardId == null)
                    continue;

                var card = StaticSessionData.AllCards.Where(x => x.Id == user.SelectedCardId).FirstOrDefault();
                cards.Add(card);
            }

            return cards;
        }

        public async Task<SessionState> GetSessionStateAsync(string roomId)
        {
            var session = await GetByRoomIdAsync(roomId);

            if (session == null)
                throw new BadHttpRequestException("No session by that id");

            return session.State;
        }

        public async Task SetSessionStateAsync(string roomId, SessionState state)
        {
            var session = await GetByRoomIdAsync(roomId);

            session.State = state;

            await StaticSessionData.SaveChangesAsync(session, roomId);
        }

        public async Task SetActiveCardsAsync(string roomId, List<int> cardIds)
        {
            var session = await GetByRoomIdAsync(roomId);

            session.CardIds.Clear();
            session.CardIds.AddRange(cardIds);

            await StaticSessionData.SaveChangesAsync(session, roomId);
        }

        public async Task<List<Entities.Card>> GetActiveCardsAsync(string roomId)
        {
            var session = await GetByRoomIdAsync(roomId);

            var activeCards = new List<Entities.Card>();

            foreach (var id in session.CardIds)
            {
                var card = StaticSessionData.AllCards.Where(x => x.Id == id).FirstOrDefault();
                if (card != null)
                    activeCards.Add(card);
            }

            return activeCards;
        }

        public async Task RemoveSession(string roomId)
        {
            await StaticSessionData.RemoveSessionAsync(roomId);
        }

        public async Task UpdateTimeStampAsync(string roomId, DateTime time)
        {
            var session = await context.Sessions.FirstOrDefaultAsync(x => x.RoomId == roomId);
            session.LastLogin = time;
            await context.SaveChangesAsync();
        }

        public async Task<Entities.Session> GetSessionFromDb(string roomId)
        {
            return await context.Sessions.FirstOrDefaultAsync(x => x.RoomId == roomId);
        }

        public async Task UpdateAsync(Session session)
        {
            await StaticSessionData.SaveChangesAsync(session, session.RoomId);
        }

        public async Task SetLastTimerAsync(string roomId)
        { 
            var session = await StaticSessionData.GetSessionAsync(roomId);
            session.LastTimer = DateTime.UtcNow;
        }

        public async Task<DateTime> GetLastTimerAsync(string roomId)
        {
            var session = await StaticSessionData.GetSessionAsync(roomId);
            return session.LastTimer;
        }

    }
}
