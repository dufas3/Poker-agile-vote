using MediatR;
using PokerFace.Data.Common;
using PokerFace.Data.Entities;

namespace PokerFace.Commands.Cards
{
    public class GetActiveCardsCommand : IRequest<List<Card>>
    {
        public int RoomId { get; set; }
    }

    public class GetActiveCardsCommandHandler : IRequestHandler<GetActiveCardsCommand, List<Card>>
    {
        private readonly ICardsRepository cardsRepository;

        public GetActiveCardsCommandHandler(ICardsRepository cardsRepository)
        {
            this.cardsRepository = cardsRepository;
        }

        public async Task<List<Card>> Handle(GetActiveCardsCommand request, CancellationToken cancellationToken)
        {
            return await cardsRepository.GetActiveCardsAsync(request.RoomId);
        }
    }
}
