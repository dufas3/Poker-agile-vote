using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.Session
{
    //moderators log's out or inactivity period reaches, session is deleted
    public class LogoutSessionCommand : IRequest
    {
        public int RoomId { get; set; }
    }

    public class LogoutSessionCommandHandler : IRequestHandler<LogoutSessionCommand>
    {
        private readonly ISessionRepository sessionRepository;

        public LogoutSessionCommandHandler(ISessionRepository sessionRepository)
        {
            this.sessionRepository = sessionRepository;
        }

        public async Task<Unit> Handle(LogoutSessionCommand request, CancellationToken cancellationToken)
        {
            await sessionRepository.LogoutSessionAsync(request.RoomId);
            return Unit.Value;
        }
    }
}
