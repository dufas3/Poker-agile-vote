using MediatR;
using PokerFace.Data;
using PokerFace.Data.Entities;
using PokerFace.Data.Repositories;

namespace PokerFace.Commands.User
{
    public class GetUsersCommand : IRequest<List<Data.Entities.User>>
    {
    }

    public class GetUsersCommandHandler : IRequestHandler<GetUsersCommand, List<Data.Entities.User>>
    {
        private readonly UsersRepository usersRepository;
        public GetUsersCommandHandler(UsersRepository usersRepository)
        {
            this.usersRepository = usersRepository;
        }
        public async Task<List<Data.Entities.User>> Handle(GetUsersCommand request, CancellationToken cancellationToken)
        {
            /*var users =  await usersRepository.GetAllAsync();
            if (users == null)
                throw new BadHttpRequestException("No users");
            return users;*/
            return UsersRepository.StaticUsers;
        }
    }
}
