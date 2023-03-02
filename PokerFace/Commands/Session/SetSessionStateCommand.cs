using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.Session
{

    public class SetSessionStateCommand : IRequest
    {
        public int RoomId { get; set; }
        public SessionState State { get; set; }
    }

    public class SetSessionStateCommandHanlder : IRequestHandler<SetSessionStateCommand>
    {
        private readonly ISessionRepository sessionRepository;

        public SetSessionStateCommandHanlder(ISessionRepository sessionRepository)
        {
            this.sessionRepository = sessionRepository;
        }

        public async Task<Unit> Handle(SetSessionStateCommand request, CancellationToken cancellationToken)
        {
            await sessionRepository.SetSessionStateAsync(request.RoomId, request.State);
            return Unit.Value;
        }
    }
}
