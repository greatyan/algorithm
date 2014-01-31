
package com.actuate.wyan;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.actuate.javaserver.soap.JavaserverSoapUtil;
import com.actuate.javaserver.utils.EncycServerStubPool;
import com.actuate.javaserver.utils.EncycServerStubProxy;
import com.actuate.schemas.actuate11.javaserver.File;
import com.actuate.schemas.actuate11.javaserver.GetFileInfo;
import com.actuate.schemas.actuate11.javaserver.GetFileInfoResponse;
import com.actuate.schemas.actuate11.javaserver.wsdl.ActuateSoapBindingStub;

public class TestLogger
{

	static int TEST_COUNT = 1024 * 1024;
	static int THREAD_COUNT = 64;

	static Logger logger = Logger.getLogger( TestLogger.class.getName( ) );

	public static void main( String[] args )
	{
		Logger.getLogger( "" ).setLevel( Level.WARNING );
		sendRequest( );

		final ExecutorService pool = Executors
				.newFixedThreadPool( THREAD_COUNT );

		for ( int i = 0; i < THREAD_COUNT; i++ )
		{
			pool.execute( new Runnable( ) {

				public void run( )
				{
					long totalTime = 0;
					for ( int i = 0; i < TEST_COUNT; i++ )
					{
						totalTime += sendRequest( );
						processRequest( );
						if ( i % 100 == 0 )
						{
							System.out.println( "TIME:" + i + ":"
									+ ( totalTime / ( i + 1 ) ) );
						}
					}
					System.out.println( totalTime / TEST_COUNT );
				}
			} );
		}
		Timer timer = new Timer( );
		timer.schedule( new TimerTask( ) {

			long start = System.currentTimeMillis( );

			@Override
			public void run( )
			{
				long end = System.currentTimeMillis( );
				System.out.println( "CLOCK:" + ( end - start ) );
				start = end;
			}

		}, 0, 5000 );;
	}

	static String IDAPI_URL = "http://baldur:11100";
	static EncycServerStubPool idapiPool = EncycServerStubPool.getInstance( );
	static
	{
		idapiPool.setMaxPoolSize( 10 );
		idapiPool.setMinPoolSize( 4 );
	}

	static int sendRequest( )
	{
		long start = System.currentTimeMillis( );// JremUtil.startTimer( logger,
													// "A", "B" );
		String volumeName = "Default Volume";
		String authID = "2cwqOnGzNy6y/SnnFk0rU5cvuXR43zf0cfMOutp7qK92SO+Uyh2fbRI99KVhbqnLsMHb3sK7OS/NsB0g1/SBKERWzPbigdkgnBK7M0gJAuHx/Qr1wX6Cznz/+eC7qcuSFOTi1a9X4O8r6qCo8Lg0QKeS6PDj6G7ybZYxEnFmUJ84uXLXzuHCk8HBkjOm9OUjThjLcyeczSdq/OlsTcKw72fjvY1chCsDcWbhRJwCsc0=";
		EncycServerStubProxy serverProxy = (EncycServerStubProxy) idapiPool
				.getProxyObject( IDAPI_URL );
		try
		{
			ActuateSoapBindingStub port = serverProxy.getProxyPort( );
			port.setHeader( null, "TargetVolume", volumeName );
			port.setHeader( null, "AuthId", authID );

			GetFileInfo request = new GetFileInfo( );
			request.setFileName( "/Home/administrator/Examples.HTML" );
			GetFileInfoResponse response = port.getFileInfo( request );
			if ( response != null )
			{
				response.getPhysicalPath( );
				File fileInfo = response.getFileInfo( );

				if ( fileInfo != null )
				{
					fileInfo.getDescription( );
					fileInfo.getTimeStamp( );
					fileInfo.getId( );
					fileInfo.getOwner( );
					fileInfo.getPageCount( ).longValue( );
					fileInfo.getSize( ).longValue( );

					fileInfo.getVersion( );
					fileInfo.getVersionName( );
					fileInfo.getUserPermissions( );
					fileInfo.getFileType( );
					JavaserverSoapUtil.convertCommaSepStringToList( response
							.getUserACL( ) );
				}
			}
		}
		catch ( Exception e )
		{
			logger.log( Level.WARNING, e.getLocalizedMessage( ), e );
		}
		finally
		{
			idapiPool.releaseProxyObject( serverProxy );
			// JremUtil.endTimer( logger, "A", "B", start );
		}
		long end = System.currentTimeMillis( );
		return (int) ( end - start );
	}

	static void processRequest( )
	{
		long start = System.currentTimeMillis( );
		do
		{
			StringBuilder sb = new StringBuilder( );
			for ( int i = 0; i < 1024; i++ )
			{
				sb.append( (char) i );
			}
		} while ( System.currentTimeMillis( ) < start + 200 );
	}
}
