using MediatR;
using PokerFace.Commands.User;
using PokerFace.Data.Common;

namespace PokerFace.Commands.Session
{
    public class GetSessionUsersCommand : IRequest<List<UserDto>>
    {
        public int Id { get; set; }
    }

    public class GetSessionUsersCommandHandler : IRequestHandler<GetSessionUsersCommand, List<UserDto>>
    {
        private readonly ISessionRepository sessionRepository;
        private readonly ICardsRepository cardsRepository;

        public GetSessionUsersCommandHandler(ISessionRepository sessionRepository, ICardsRepository cardsRepository)
        {
            this.sessionRepository = sessionRepository;
            this.cardsRepository = cardsRepository;
        }

        public async Task<List<UserDto>> Handle(GetSessionUsersCommand request, CancellationToken cancellationToken)
        {
            var users = await sessionRepository.GetSessionUsersAsync(request.Id);

            var userDtos = users.Select(x => x.ToUserDto()).ToList();

            foreach (var userDto in userDtos)
            {
                var user = users.Where(x => x.Id == userDto.Id).FirstOrDefault();
                userDto.SelectedCard = await cardsRepository.GetAsync(user.SelectedCardId.Value);s
            }

            return userDtos;
        }
    }
}
