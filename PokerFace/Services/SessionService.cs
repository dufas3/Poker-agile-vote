using PokerFace.Data;
using PokerFace.Data.Common;
using PokerFace.Data.Entities;
using PokerFace.Commands.Email;

namespace PokerFace.Services
{
    public class SessionService : ISessionService
    {
        private readonly ISessionRepository sessionRepository;
        private readonly IUserRepository userRepository;
        private readonly IEmailSender _emailSender;

        public SessionService(ISessionRepository sessionRepository, IUserRepository userRepository, IEmailSender emailSender)
        {
            this.sessionRepository = sessionRepository;
            this.userRepository = userRepository;
            _emailSender = emailSender;
        }
        public async Task CreateSession(int moderatorId) 
        {
            var roomId = Guid.NewGuid();

            var session = new Session
            {
                ModeratorId = moderatorId,
                RoomId = roomId.ToString()
            };
            var user = await userRepository.GetAsync(moderatorId);
            var link = "http://localhost:3000/?room=" + roomId;
            user.RoomId = roomId.ToString();

            var message = new Message(new string[] { user.Name }, "FESTO Scrum Poker", "Dear Moderator,\n\nYou have created new voting room, its unique link is: " + link + "\nPlease use it to access this room. This link can be shared with other players to access the same room.");
            _emailSender.SendEmail(message);

            await sessionRepository.AddAsync(session);
            await userRepository.UpdateAsync(user);

        }
    }
}
