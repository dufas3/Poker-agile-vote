using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.Session
{
    public class ClearSessionVotesCommand : IRequest
    {
        public int RoomId { get; set; }
    }

    public class ClearVotesCommandHandler : IRequestHandler<ClearSessionVotesCommand>
    {
        private readonly ISessionRepository sessionRepository;

        public ClearVotesCommandHandler(ISessionRepository sessionRepository)
        {
            this.sessionRepository = sessionRepository;
        }

        public async Task<Unit> Handle(ClearSessionVotesCommand request, CancellationToken cancellationToken)
        {
            await sessionRepository.ClearVotes(request.RoomId);
            return Unit.Value;
        }
    }
}
