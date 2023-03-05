using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.Session
{
    //active session cards
    public class GetSessionCommand : IRequest<Data.Entities.Session>
    {
        public int RoomId { get; set; }
    }

    public class GetSessionCommandHandler : IRequestHandler<GetSessionCommand, Data.Entities.Session>
    {
        private readonly ISessionRepository sessionRepository;

        public GetSessionCommandHandler(ISessionRepository sessionRepository)
        {
            this.sessionRepository = sessionRepository;
        }

        public async Task<Data.Entities.Session> Handle(GetSessionCommand request, CancellationToken cancellationToken)
        {
            return await sessionRepository.GetByRoomIdAsync(request.RoomId);
        }
    }
}
