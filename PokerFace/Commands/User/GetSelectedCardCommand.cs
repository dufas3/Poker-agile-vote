using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.User
{
    public class GetSelectedCardCommand : IRequest<int>
    {
        public int UserId { get; set; }
    }

    public class GetSelectedCardCommandHanlder : IRequestHandler<GetSelectedCardCommand, int>
    {
        private readonly IUserRepository userRepository;

        public GetSelectedCardCommandHanlder(IUserRepository userRepository)
        {
            this.userRepository = userRepository;
        }

        public async Task<int> Handle(GetSelectedCardCommand request, CancellationToken cancellationToken)
        {
            return await userRepository.GetSelectedCardAsync(request.UserId);
        }
    }
}
