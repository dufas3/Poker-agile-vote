using PokerFace.Data;
using PokerFace.Data.Common;
using PokerFace.Data.Entities;

namespace PokerFace.Services
{
    public class SessionService : ISessionService
    {
        private readonly ISessionRepository sessionRepository;
        private readonly IUserRepository userRepository;
       
        public SessionService(ISessionRepository sessionRepository, IUserRepository userRepository)
        {
            this.sessionRepository = sessionRepository;
            this.userRepository = userRepository;
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

            await sessionRepository.AddAsync(session);
            await userRepository.UpdateAsync(user);
        }
    }
}
