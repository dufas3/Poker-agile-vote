using PokerFace.Commands.User;

namespace PokerFace.Commands
{
    public static class DtoExtensions
    {
        public static ModeratorDto ToModeratorDto(this Data.Entities.User user)
        {
            return new ModeratorDto()
            {
                Email = user.Name,
                Id = user.Id
            };
        }
        public static UserDto ToUserDto(this Data.Entities.User user)
        {
            return new UserDto()
            {
                Name = user.Name,
                Id = user.Id,
                SelectedCard = null
            };
        }
    }
}
