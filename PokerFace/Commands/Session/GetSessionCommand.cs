using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.Session
{
    public class GetSessionCommand : IRequest<SessionDto>
    {
        public string RoomId { get; set; }
    }

    public class GetSessionCommandHandler : IRequestHandler<GetSessionCommand, SessionDto>
    {
        private readonly ISessionRepository sessionRepository;
        private readonly ICardsRepository cardsRepository;
        private readonly IUserRepository userRepository;

        public GetSessionCommandHandler(ISessionRepository sessionRepository, IUserRepository userRepository, ICardsRepository cardsRepository)
        {
            this.sessionRepository = sessionRepository;
            this.cardsRepository = cardsRepository;
            this.userRepository = userRepository;
        }

        public async Task<SessionDto> Handle(GetSessionCommand request, CancellationToken cancellationToken)
        {
            var session = await sessionRepository.GetByRoomIdAsync(request.RoomId);
            var dto = session.ToDto();

            foreach (var cardId in session.CardIds)
            {
                var card = await cardsRepository.GetAsync(cardId);
                if (card != null)
                    dto.Cards.Add(card);
            }

            foreach (var userId in session.UserIds)
            {
                var user = await userRepository.GetAsync(userId);
                if (user != null)
                    dto.Users.Add(user);
            }

            return dto;
        }
    }
}
