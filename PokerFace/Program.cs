using MediatR;
using PokerFace.Data;
using PokerFace.Data.Common;
using PokerFace.Data.Repositories;

namespace PokerFace.Web
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);

            builder.Services.AddMediatR(typeof(Program));

            builder.Services.AddControllers();
            builder.Services.AddEndpointsApiExplorer();

            builder.Services.AddDbContext<ApplicationDbContext>();
            builder.Services.AddScoped<IUserRepository, UserRepository>();
            builder.Services.AddScoped<ISessionRepository, SessionRepository>();
            builder.Services.AddScoped<ICardsRepository, CardsRepository>();
            
            //for adding static data 
            //builder.Services.AddSingleton(new StaticData(builder.Services.BuildServiceProvider().GetService<ApplicationDbContext>()));

            builder.Services.AddCors(options =>
            {
                options.AddPolicy(name: "MyCors",
                                  policy =>
                                  {
                                      policy.AllowAnyOrigin();
                                      policy.AllowAnyHeader();
                                      policy.AllowAnyMethod();
                                  });
            });

            var app = builder.Build();

            app.UseCors("MyCors");

            app.UseHttpsRedirection();

            app.UseAuthorization();

            app.MapControllers();

            app.Run();
        }
    }
}