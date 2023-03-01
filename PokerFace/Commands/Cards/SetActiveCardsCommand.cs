using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.Cards
{
    public class SetActiveCardsCommand : IRequest
    {
        public int RoomId { get; set; }
        public List<int> Cards { get; set; }
    }

    public class SetActiveCardsCommandHandler : IRequestHandler<SetActiveCardsCommand>
    {
        private readonly ICardsRepository cardsRepository;

        public SetActiveCardsCommandHandler(ICardsRepository cardsRepository)
        {
            this.cardsRepository = cardsRepository;
        }

        public async Task<Unit> Handle(SetActiveCardsCommand request, CancellationToken cancellationToken)
        {
            await cardsRepository.SetActiveCardsAsync(request.RoomId, request.Cards);
            return Unit.Value;
        }
    }
}
