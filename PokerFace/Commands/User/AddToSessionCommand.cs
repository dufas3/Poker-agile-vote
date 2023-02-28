using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.User
{
    //will create new user here
    public class AddToSessionCommand : IRequest<UserDto>
    {
        public string Name { get; set; }
        public int RoomId { get; set; }
    }

    public class AddToSessionCommandHandler : IRequestHandler<AddToSessionCommand, UserDto>
    {
        ISessionRepository sessionRepository { get; set; }
        IUserRepository userRepository { get; set; }

        public AddToSessionCommandHandler(ISessionRepository sessionRepository, IUserRepository userRepository)
        {
            this.userRepository = userRepository;
            this.sessionRepository = sessionRepository;
        }
        public async Task<UserDto> Handle(AddToSessionCommand request, CancellationToken cancellationToken)
        {
            //check if session existing
            var session = await sessionRepository.GetAsync(request.RoomId);
            if (session == null)
                throw new BadHttpRequestException("Session not found!");

            var user = new Data.Entities.User { Name = request.Name, RoomId = request.RoomId };

            await userRepository.AddUserToSessionAsync(user, request.RoomId);

            return user.ToUserDto();
        }
    }
}
