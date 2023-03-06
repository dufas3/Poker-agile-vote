using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.Session
{
    //active session cards
    public class GetSessionCommand : IRequest<SessionDto>
    {
        public string RoomId { get; set; }
    }

    public class GetSessionCommandHandler : IRequestHandler<GetSessionCommand, SessionDto>
    {
        private readonly ISessionRepository sessionRepository;

        public GetSessionCommandHandler(ISessionRepository sessionRepository)
        {
            this.sessionRepository = sessionRepository;
        }

        public async Task<SessionDto> Handle(GetSessionCommand request, CancellationToken cancellationToken)
        {
            var session = await sessionRepository.GetByRoomIdAsync(request.RoomId);
            return session.ToDto();
        }
    }
}
