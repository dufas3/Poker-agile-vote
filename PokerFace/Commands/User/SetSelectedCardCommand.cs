using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.User
{
    public class SetSelectedCardCommand : IRequest
    {
        public int UserId { get; set; }
        public int CardId { get; set; }
    }

    public class SetSelectedCardCommandHandler : IRequestHandler<SetSelectedCardCommand>
    {
        private readonly IUserRepository userRepository;

        public SetSelectedCardCommandHandler(IUserRepository userRepository)
        {
            this.userRepository = userRepository;
        }

        public async Task<Unit> Handle(SetSelectedCardCommand request, CancellationToken cancellationToken)
        {
            await userRepository.SetSelectedCardAsync(request.UserId, request.CardId);
            return Unit.Value;
        }
    }
}
