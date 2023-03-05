using MediatR;
using PokerFace.Data.Common;
using PokerFace.Data.Hubs;

namespace PokerFace.Commands.User
{
    //will create new user here
    public class AddToSessionCommand : IRequest<UserDto>
    {
        public string Name { get; set; }
        public int RoomId { get; set; }
        public string SocketId { get; set; }
    }

    public class AddToSessionCommandHandler : IRequestHandler<AddToSessionCommand, UserDto>
    {
        private readonly IUserRepository userRepository;
        private readonly ISignalRService signalRService;

        public AddToSessionCommandHandler(IUserRepository userRepository, ISignalRService signalRService)
        {
            this.userRepository = userRepository;
            this.signalRService = signalRService;
        }

        public async Task<UserDto> Handle(AddToSessionCommand request, CancellationToken cancellationToken)
        {
            var user = await userRepository.GetAsync(request.SocketId);
            if (user == null)
                throw new BadHttpRequestException("no socket by that id");
            user.Name = request.Name;
            user.RoomId = request.RoomId;

            await userRepository.AddUserToSessionAsync(user, request.RoomId);

            await signalRService.SendMessage(StaticHubMethodNames.SendPlayerListUpdate, request.RoomId);

            return user.ToUserDto();
        }
    }
}
