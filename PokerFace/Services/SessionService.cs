using PokerFace.Data.Common;
using PokerFace.Data.Entities;
using PokerFace.Commands.Email;
using PokerFace.Data;

namespace PokerFace.Services
{
    public class SessionService : ISessionService
    {
        private readonly ISessionRepository sessionRepository;
        private readonly IUserRepository userRepository;
        private readonly IEmailSender _emailSender;
        private readonly ICardsRepository cardsRepository;
        private readonly ISettingsRepository settingsRepository;

        public SessionService(ISessionRepository sessionRepository, IUserRepository userRepository, IEmailSender emailSender, ICardsRepository cardsRepository, ISettingsRepository settingsRepository)
        {
            this.sessionRepository = sessionRepository;
            this.userRepository = userRepository;
            _emailSender = emailSender;
            this.cardsRepository = cardsRepository;
            this.settingsRepository = settingsRepository;
        }

        public async Task CreateSession(Moderator moderator)
        {
            var roomId = Guid.NewGuid().ToString();

            bool isSessionNew = true;

            if (moderator.RoomId != null)
            {
                var temp = await sessionRepository.GetSessionFromDb(moderator.RoomId);
                isSessionNew = temp == null;
            }

            if (isSessionNew)
                moderator.RoomId = roomId;
            
            var settings = await settingsRepository.GetSettingsAsync();

            var dbSession = new Session
            {
                ModeratorId = moderator.Id,
                RoomId = moderator.RoomId,
                LastLogin = DateTime.Now
            };

           

            var link = "https://pokerface-dev.azurewebsites.net/?roomId=" + moderator.RoomId;

            var message = new Message(new string[] { moderator.Name }, "FESTO Scrum Poker", "Dear Moderator,\n\nYou have created new voting room, its unique link is: " + link + "\nPlease use it to access this room. This link can be shared with other players to access the same room.");
            await _emailSender.SendEmailAsync(message);

            if (isSessionNew)
                await sessionRepository.AddAsync(dbSession);
            await sessionRepository.AddAsync(moderator, settings);

            if (isSessionNew)
                await userRepository.UpdateModeratorAsync(moderator);

            if (StaticSessionData.AllCards.Count == 0)
                await cardsRepository.SetCardsAsync();

        }

        public async Task LogoutSessionAsync(string roomId)
        {
            await sessionRepository.RemoveSession(roomId);
        }

        public async Task LogoutSessionUserAsync(string roomId, int userId)
        {
            var user = await userRepository.GetAsync(userId, roomId);
            await userRepository.DeleteAsync(user);
        }

        public async Task ClearVotes(string roomId)
        {
            var users = await sessionRepository.GetSessionUsersAsync(roomId);

            users.ForEach(x => x.SelectedCardId = null);

            await StaticSessionData.SaveChangesAsync(users, roomId);
        }
    }
}
