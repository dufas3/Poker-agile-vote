using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.Cards
{
    public class SetActiveUserCardCommand : IRequest
    {
        public int UserId { get; set; }
        public int CardId { get; set; }
    }

    public class SetActiveUserCardCommandHandler : IRequestHandler<SetActiveUserCardCommand>
    {
        private readonly ICardsRepository cardsRepository;

        public SetActiveUserCardCommandHandler(ICardsRepository cardsRepository)
        {
            this.cardsRepository = cardsRepository;
        }

        public async Task<Unit> Handle(SetActiveUserCardCommand request, CancellationToken cancellationToken)
        {
            await cardsRepository.SetActiveUserCardAsync(request.CardId, request.UserId);
            return Unit.Value;
        }
    }
}
