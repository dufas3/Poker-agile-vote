using Microsoft.EntityFrameworkCore;
using PokerFace.Data.Common;
using PokerFace.Data.Entities;

namespace PokerFace.Data.Repositories
{
    public class SessionRepository : ISessionRepository
    {
        private readonly ApplicationDbContext context;

        public SessionRepository(ApplicationDbContext context)
        {
            this.context = context;
        }

        public async Task<List<Session>> GetAllAsync()
        {
            return await Task.FromResult(context.Sessions.ToList());
        }

        public async Task<List<User>> GetSessionUsersAsync(string roomId)
        {
            var session = await GetByRoomIdAsync(roomId);
            if (session == null)
                throw new BadHttpRequestException("No session by that id");

            var users = new List<User>();

            users = await Task.FromResult(context.Users.Where(x => x.RoomId == roomId).ToList());

            return users;
        }

        public async Task<Session> GetAsync(int id)
        {
            return await Task.FromResult(context.Sessions.Where(x => x.Id == id).First());
        }

        public async Task<Session> GetByRoomIdAsync(string id)
        {
            return await Task.FromResult(context.Sessions.Where(x => x.RoomId == id).First());
        }

        public async Task AddAsync(Session session)
        {
            await context.Sessions.AddAsync(session);
            await context.SaveChangesAsync();
        }

        public async Task LogoutSessionAsync(string roomId)
        {
            var session = await GetByRoomIdAsync(roomId);

            if (session == null)
                throw new BadHttpRequestException("No session by that id");

            var users = await Task.FromResult(context.Users.Where(x => x.RoomId == roomId).ToList());

            context.Users.RemoveRange(users);
            context.Sessions.Remove(session);

            await context.SaveChangesAsync();
        }

        public async Task<List<Card>> GetUserSelectedCardsAsync(string roomId)
        {
            var users = await GetSessionUsersAsync(roomId);

            var cards = new List<Card>();

            foreach (var user in users)
            {
                //not selected card
                if (user.SelectedCardId == null)
                    continue;

                var card = await context.Cards.FirstOrDefaultAsync(x => x.Id == user.SelectedCardId);
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

            if (session == null)
                throw new BadHttpRequestException("No session by that id");

            session.State = state;

            context.SaveChanges();
        }

        public async Task ClearVotes(string roomId)
        {
            var session = await GetByRoomIdAsync(roomId);

            if (session == null)
                throw new BadHttpRequestException("No session by that id");

            var users = new List<User>();
            foreach (var id in session.UserIds)
            {
                var user = await context.Users.FirstOrDefaultAsync(x => x.Id == id);
                if (user == null)
                    continue;

                user.SelectedCardId = null;
                users.Add(user);
            }
            context.Users.UpdateRange(users);

            await context.SaveChangesAsync();
        }

        public async Task SetActiveCardsAsync(string roomId, List<int> cardIds)
        {
            var session = await GetByRoomIdAsync(roomId);

            if (session == null)
                throw new BadHttpRequestException("There's no session with this Id!");

            session.CardIds.Clear();
            session.CardIds.AddRange(cardIds);

            context.Sessions.Update(session);
            await context.SaveChangesAsync();
        }

        public async Task<List<Card>> GetActiveCardsAsync(string roomId)
        {
            var session = await GetByRoomIdAsync(roomId);

            if (session == null)
                throw new BadHttpRequestException("There's no session with this Id!");

            var activeCards = new List<Card>();

            foreach (var id in session.CardIds)
            {
                var card = await context.Cards.FirstOrDefaultAsync(x => x.Id == id);
                if (card != null)
                    activeCards.Add(card);
            }

            return activeCards;
        }

        public async Task LogoutUserAsync(int userId, string roomId)
        {
            var session = await GetByRoomIdAsync(roomId);
            if (session == null)
                throw new BadHttpRequestException("There's no session with this Id!");

            var user = await context.Users.FirstOrDefaultAsync(x => x.Id == userId);
            if (user == null)
                throw new BadHttpRequestException("There's no user with this Id!");

            session.UserIds.Remove(user.Id);

            context.Update(session);
            context.Users.Remove(user);
            await context.SaveChangesAsync();
        }

        public async Task<int> GetModeratorId(string roomId)
        {
            return context.Sessions.FirstOrDefault(x => x.RoomId == roomId).ModeratorId;
        }
    }
}
