package jdbc.jdbc;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.jdbc.connection.SingleConnection;

/**
 * Unit test for simple App.
 */
public class TestConnection 
{
    @Test
    public void connectPostgres()
    {
        SingleConnection.getConnection();
    }
}
