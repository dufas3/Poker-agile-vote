using PokerFace.Data;
using PokerFace.Data.Common;
using PokerFace.Data.Entities;

namespace PokerFace.Services
{
    public class SessionService : ISessionService
    {
        private readonly ISessionRepository sessionRepository ;
       
        public SessionService(ISessionRepository sessionRepository)
        {
            this.sessionRepository = sessionRepository;
        }

        public async Task CreateSession(int moderatorId) 
        {
            var roomId = Guid.NewGuid();

            var session = new Session
            {
                ModeratorId = moderatorId,
                RoomId = roomId.ToString()
            };

            await sessionRepository.AddAsync(session);
        }
    }
}
