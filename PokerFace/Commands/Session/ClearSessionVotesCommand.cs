using MediatR;
using PokerFace.Data.Common;
using PokerFace.Data.Hubs;

namespace PokerFace.Commands.Session
{
    public class ClearSessionVotesCommand : IRequest
    {
        public string RoomId { get; set; }
    }

    public class ClearVotesCommandHandler : IRequestHandler<ClearSessionVotesCommand>
    {
        private readonly ISessionRepository sessionRepository; 
        private readonly ISignalRService signalRService;

        public ClearVotesCommandHandler(ISessionRepository sessionRepository, ISignalRService signalRService)
        {
            this.sessionRepository = sessionRepository;
            this.signalRService = signalRService;

        }

        public async Task<Unit> Handle(ClearSessionVotesCommand request, CancellationToken cancellationToken)
        {
            await signalRService.SendMessage(StaticHubMethodNames.SendPlayerListUpdate, request.RoomId);
            await sessionRepository.ClearVotes(request.RoomId);
            return Unit.Value;
        }
    }
}
