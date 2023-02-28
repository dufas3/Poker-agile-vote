using PokerFace.Commands.User;

namespace PokerFace.Commands
{
    public static class DtoExtensions
    {
        public static ModeratorDto ToDto(this Data.Entities.User user)
        {
            return new ModeratorDto()
            {
                Email = user.Name,
                Id = user.Id
            };
        }
    }
}
