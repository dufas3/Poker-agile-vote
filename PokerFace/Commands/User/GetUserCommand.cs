﻿using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.User
{
    public class GetUserCommand : IRequest<UserDto>
    {
        public int Id { get; set; }
    }

    public class GetUserCommandHanlder : IRequestHandler<GetUserCommand, UserDto>
    {
        private readonly IUserRepository userRepository;

        public GetUserCommandHanlder(IUserRepository userRepository)
        {
            this.userRepository = userRepository;
        }

        public async Task<UserDto> Handle(GetUserCommand request, CancellationToken cancellationToken)
        {
            var user = await userRepository.GetAsync(request.Id);

            return user.ToUserDto();
        }
    }
}
