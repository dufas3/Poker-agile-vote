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

        public GetSessionUsersCommandHandler(ISessionRepository sessionRepository)
        {
            this.sessionRepository = sessionRepository;
        }

        public async Task<List<UserDto>> Handle(GetSessionUsersCommand request, CancellationToken cancellationToken)
        {
            var users = await sessionRepository.GetSessionUsersAsync(request.Id);

            return users.Select(x => x.ToUserDto()).ToList();
        }
    }
}
