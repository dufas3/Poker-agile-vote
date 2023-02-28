using MediatR;
using Microsoft.AspNetCore.Mvc;
using PokerFace.Data;

namespace PokerFace.Commands.User
{
    public class GetModeratorCommand : IRequest<ModeratorDto>
    {
        public string UserEmail { get; set; }
        public string UserPassword { get; set; }
    }

    public class GetModeratorCommandHandler : IRequestHandler<GetModeratorCommand, ModeratorDto>
    {
        private readonly ApplicationDbContext context;
        public GetModeratorCommandHandler(ApplicationDbContext context)
        {
            this.context = context;
        }
        public async Task<ModeratorDto> Handle(GetModeratorCommand request, CancellationToken cancellationToken)
        {
            var user = context.Users.First(x => x.Name == request.UserEmail);
            if (user == null || user.Password != request.UserPassword)
                return null;
            return user.ToDto();
        }
    }
}
