using PokerFace.Commands.Session;
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
                Id = user.Id,
                RoomId = user.RoomId
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

        public static SessionDto ToDto(this Data.Entities.Session session)
        {
            var sessionDto = new SessionDto()
            {
                RoomId = session.RoomId,
                State = session.State              
            };
            sessionDto.CardIds.AddRange(session.CardIds);
            sessionDto.UserIds.AddRange(session.UserIds);

            return sessionDto;
        }
    }
}
