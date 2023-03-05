using MediatR;
using PokerFace.Data.Common;
using PokerFace.Data.Hubs;

namespace PokerFace.Commands.Session
{
    public class LogoutUserCommand : IRequest
    {
        public int UserId { get; set; }
        public int RoomId { get; set; }
    }

    public class LogoutUserCommandHandler : IRequestHandler<LogoutUserCommand>
    {
        private readonly ISessionRepository sessionRepository;
        private readonly ISignalRService signalRService;

        public LogoutUserCommandHandler(ISessionRepository sessionRepository, ISignalRService signalRService)
        {
            this.sessionRepository = sessionRepository;
            this.signalRService = signalRService;
        }

        public async Task<Unit> Handle(LogoutUserCommand request, CancellationToken cancellationToken)
        {
            await sessionRepository.LogoutUserAsync(request.UserId, request.RoomId);

            await signalRService.SendMessage(StaticHubMethodNames.SendPlayerListUpdate,request.RoomId);

            return Unit.Value;
        }
    }
}
