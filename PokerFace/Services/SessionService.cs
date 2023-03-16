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
            user.RoomId = roomId.ToString();

            var message = new Message(new string[] { "pokerfacebalandziai@gmail.com" }, "Test email", "This is a test.");
            _emailSender.SendEmail(message);

            await sessionRepository.AddAsync(session);
            await userRepository.UpdateAsync(user);

        }
    }
}
