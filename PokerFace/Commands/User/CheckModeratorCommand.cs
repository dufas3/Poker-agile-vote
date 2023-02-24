using MediatR;
using PokerFace.Data;

namespace PokerFace.Commands.User
{
    public class CheckModeratorCommand : IRequest
    {
        public string UserName { get; set; }
        public string Password { get; set; }
    }

    public class CheckModeratorCommandHandler : IRequestHandler<CheckModeratorCommand, Unit>
    {
        private readonly ApplicationDbContext context;
        public CheckModeratorCommandHandler(ApplicationDbContext context)
        {
            this.context = context;
        }
        public async Task<Unit> Handle(CheckModeratorCommand request, CancellationToken cancellationToken)
        {
            var user = context.Users.First(x => x.Name == request.UserName);
            if (user == null || user.Password != request.Password)
                return Unit.Value;
            throw new BadHttpRequestException("Bad Login Attempt!");
        }
    }
}
